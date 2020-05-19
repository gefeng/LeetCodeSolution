package annotations;

import enums.QDifficulty;
import enums.QTag;

public @interface Problem {
    String title();
    QDifficulty difficulty();
    QTag tag();
    String url();
}