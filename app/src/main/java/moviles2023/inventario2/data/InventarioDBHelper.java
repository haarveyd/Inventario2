package moviles2023.inventario2.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import moviles2023.inventario2.data.ProductoContract.ProductoEntry;

public class InventarioDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Inventario2.db";

    public InventarioDBHelper( Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ProductoEntry.TABLE_NAME + " ("
                +ProductoEntry.ID + " INTEGER PRIMARY KEY,"
                +ProductoEntry.QUANTITY + " INTEGER NOT NULL,"
                +ProductoEntry.QUANTITYS + " INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveProducto(Producto producto) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                ProductoEntry.TABLE_NAME,
                null,
                producto.toContentValues());

    }

    public Cursor getAllProducto() {
        return getReadableDatabase()
                .query(
                        ProductoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getProductoById(String productoId) {
        Cursor c = getReadableDatabase().query(
                ProductoEntry.TABLE_NAME,
                null,
                ProductoEntry.ID + " LIKE ?",
                new String[]{productoId},
                null,
                null,
                null);
        return c;
    }


    public int deleteProducto(String productoId) {
        return getWritableDatabase().delete(
                ProductoEntry.TABLE_NAME,
                ProductoEntry.ID + " LIKE ?",
                new String[]{productoId});
    }

    public int updateProducto(Producto productoModificar, String productoId) {
        return getWritableDatabase().update(
                ProductoEntry.TABLE_NAME,
                productoModificar.toContentValues(),
                ProductoEntry.ID + " LIKE ?",
                new String[]{productoId}
        );
    }
}


