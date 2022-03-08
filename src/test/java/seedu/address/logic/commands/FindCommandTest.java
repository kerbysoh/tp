package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.PREDICATE_SHOW_ALL_ROLES;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.role.RoleNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyNameContainsKeywordsPredicate firstCompanyPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("first"),
                        Collections.singletonList("first"));
        CompanyNameContainsKeywordsPredicate secondCompanyPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("second"),
                        Collections.singletonList("second"));

        RoleNameContainsKeywordsPredicate firstRolePredicate =
                new RoleNameContainsKeywordsPredicate(Collections.singletonList("first"));
        RoleNameContainsKeywordsPredicate secondRolePredicate =
                new RoleNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstCompanyPredicate, firstRolePredicate);
        FindCommand findSecondCommand = new FindCommand(secondCompanyPredicate, secondRolePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstCompanyPredicate, firstRolePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 0);
        CompanyNameContainsKeywordsPredicate companyPredicate = prepareCompanyPredicate(" ");
        RoleNameContainsKeywordsPredicate rolePredicate = prepareRolePredicate(" ");
        FindCommand command = new FindCommand(companyPredicate, rolePredicate);
        expectedModel.updateFilteredCompanyList(companyPredicate, PREDICATE_SHOW_ALL_ROLES);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCompanyList());
    }

    /* @Test
    public void execute_multipleKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 3);
        CompanyNameContainsKeywordsPredicate companyPredicate = prepareCompanyPredicate("zoom apple tech");
        RoleNameContainsKeywordsPredicate rolePredicate = prepareRolePredicate("software mobile");
        FindCommand command = new FindCommand(companyPredicate, rolePredicate);
        expectedModel.updateFilteredCompanyList(companyPredicate, PREDICATE_SHOW_ALL_ROLES);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ZOOM, APPLE, GOVTECH), model.getFilteredCompanyList());
    } */

    /**
     * Parses {@code userInput} into a {@code CompanyNameContainsKeywordsPredicate}.
     */
    private CompanyNameContainsKeywordsPredicate prepareCompanyPredicate(String userInput) {
        return new CompanyNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")),
                Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RoleContainsKeywordsPredicate}.
     */
    private RoleNameContainsKeywordsPredicate prepareRolePredicate(String userInput) {
        return new RoleNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
