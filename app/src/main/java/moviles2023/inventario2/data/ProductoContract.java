package moviles2023.inventario2.data;

import android.provider.BaseColumns;

public class ProductoContract {

    public static abstract class ProductoEntry implements BaseColumns{
        public static final String TABLE_NAME ="producto";

        public static final String ID = "codigo";
        public static final String QUANTITY = "cantidad";
        public static final String QUANTITYS = "cantidadv";




    }
}
