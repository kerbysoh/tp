package seedu.address.logic.parser;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    /* @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    } */

    /* @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand
                = new FindCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")
                , Arrays.asList("Alice", "Bob"))
                , new RoleNameContainsKeywordsPredicate(Arrays.asList("Test", "Test")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    } */

}
