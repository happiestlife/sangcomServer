package capstone.sangcom.entity.dto.schoolMeals;

//import kotlin.Metadata;
//import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

//@Metadata(
//        mv = {1, 5, 1},
//        k = 1,
//        d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u0010"},
//        d2 = {"Lkr/go/neis/api/Menu;", "", "()V", "breakfast", "", "getBreakfast", "()Ljava/lang/String;", "setBreakfast", "(Ljava/lang/String;)V", "dinner", "getDinner", "setDinner", "lunch", "getLunch", "setLunch", "toString", "special module for files not under source root"}
//)

public final class Menu {
    @NotNull
    private String breakfast = "";
    @NotNull
    private String lunch = "";
    @NotNull
    private String dinner = "";

    @NotNull
    public final String getBreakfast() {
        return this.breakfast;
    }

//    public final void setBreakfast(@NotNull String var1) {
//        Intrinsics.checkNotNullParameter(var1, "<set-?>");
//        this.breakfast = var1;
//    }

    @NotNull
    public final String getLunch() {
        return this.lunch;
    }

//    public final void setLunch(@NotNull String var1) {
//        Intrinsics.checkNotNullParameter(var1, "<set-?>");
//        this.lunch = var1;
//    }

    @NotNull
    public final String getDinner() {
        return this.dinner;
    }

//    public final void setDinner(@NotNull String var1) {
//        Intrinsics.checkNotNullParameter(var1, "<set-?>");
//        this.dinner = var1;
//    }

    @NotNull
    public String toString() {
        return "[아침]" + this.breakfast + "\n[점심]" + this.lunch + "\n[저녁]" + this.dinner;
    }
}

