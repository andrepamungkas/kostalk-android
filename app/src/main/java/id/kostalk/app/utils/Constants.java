package id.kostalk.app.utils;

/**
 * Created by andre on 3/23/2018.
 */

public final class Constants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;
    public static final int API_STATUS_CODE_OK = 200;
    public static final int API_STATUS_CODE_BAD_REQUEST = 403;
    public static final int API_STATUS_CODE_NOT_FOUND = 404;
    public static final int API_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;

    public static final String DB_NAME = "ekos.db";
    public static final String PREF_NAME = "ekos_pref";

    public static final long NULL_INDEX = -1L;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    private Constants() {
        // This utility class is not publicly instantiable
    }
}
