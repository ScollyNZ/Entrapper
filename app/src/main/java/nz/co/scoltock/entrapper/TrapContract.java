package nz.co.scoltock.entrapper;

import android.provider.BaseColumns;

public final class TrapContract {

    public TrapContract() {}

        /* Inner class that defines the table contents */
        public static abstract class Trap implements BaseColumns {
            public static final String TABLE_NAME = "trap";
            public static final String COLUMN_NAME_CODE = "code";
            public static final String COLUMN_NAME_CURRENT_LAT = "currentlat";
            public static final String COLUMN_NAME_CURRENT_LON = "currentlon";
            public static final String COLUMN_NAME_LAST_VISIT = "lastvisit";

            private static final String TEXT_TYPE = " TEXT";
            private static final String DBL_TYPE = " DOUBLE";
            private static final String COMMA_SEP = ",";
            public static final String SQL_SELECT_ALL_TRAPS = "SELECT * FROM " + Trap.TABLE_NAME;
            public static final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE " + Trap.TABLE_NAME + " (" +
                            Trap._ID + " INTEGER PRIMARY KEY," +
                            Trap.COLUMN_NAME_CODE + TEXT_TYPE + COMMA_SEP +
                            Trap.COLUMN_NAME_CURRENT_LAT + DBL_TYPE + COMMA_SEP +
                            Trap.COLUMN_NAME_CURRENT_LON + DBL_TYPE + COMMA_SEP +
                            Trap.COLUMN_NAME_LAST_VISIT + TEXT_TYPE +
                    " )";

            public static final String SQL_DELETE_ENTRIES =
                    "DROP TABLE IF EXISTS " + Trap.TABLE_NAME;

            private int id;
            private String code;
            private double currentLat;
            private double currentLon;

            public Trap(){}

            public Trap(Integer id,String code) {
                super();
                this.id = id;
                this.code = code;
            }

            //getters & setters

            @Override
            public String toString() {
                return "Trap [id=" + id + ", code=" + code + "]";
            }


        }
    }

