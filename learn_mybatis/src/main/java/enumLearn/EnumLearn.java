package enumLearn;

/**
 * Created by k on 2018/7/28.
 */
public enum EnumLearn {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");
    private String name;

    private EnumLearn(String desc) {
        this.name = desc;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        for (EnumLearn en :
                EnumLearn.values()) {
            System.out.println(en.getName());
        }
    }
}
