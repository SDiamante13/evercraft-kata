package tech.pathtoprogramming.evercraft.model;

import org.approvaltests.core.Options;
import org.approvaltests.legacycode.Range;
import org.junit.jupiter.api.Test;

import static org.approvaltests.Approvals.verifyAll;

class AbilitiesApprovalTest {

    @Test
    void modifiers() {
        Integer[] abilities = Range.get(1, 20);

        String title = "| Ability | Modifier |\n" +
                "--- | --- |";
        Options options = new Options().forFile().withExtension(".md").withScrubber(s -> s.replace("\n\n", ""));
        verifyAll(
                title,
                abilities,
                (a) -> String.format("| %s | %s |",
                        a, new AbilityScore(a).modifier()), options);
    }
}
