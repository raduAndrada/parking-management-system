package ro.upet.parking.system.management.util;


import java.util.Objects;

@FunctionalInterface
interface IMethodCaller {

    Object callMethod();
}


public class TestUtil {

    static Object changeNullToDefault(final Object[] args, final int index, final IMethodCaller methodCaller) {
        if (args.length >= index) {
            return Objects.nonNull(args[index]) ? args[index] : methodCaller.callMethod();
        }
        return methodCaller.callMethod();
    }


    static String changeNullStringToDefault(final Object[] args, final int index, final String original) {
        if (args.length > index) {
            return Objects.nonNull(args[index]) ? (String) args[index] : original;
        }
        return original;
    }

    static Boolean changeNullBooleanToDefault(final Object[] args, final int index, final Boolean original) {
        if (args.length >= index) {
            return Objects.nonNull(args[index]) ? (Boolean) args[index] : original;
        }
        return original;
    }
}
