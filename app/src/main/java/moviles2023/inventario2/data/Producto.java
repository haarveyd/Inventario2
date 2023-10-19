package moviles2023.inventario2.data;


import android.content.ContentValues;
import android.database.Cursor;
import moviles2023.inventario2.data.ProductoContract.ProductoEntry;

public class Producto {

    private int id;
    private int quantity;
    private int quantitys;

    public Producto(int id, int quantity, int quantitys) {
        this.id = id;
        this.quantity = quantity;
        this.quantitys = quantitys;
    }

    public Producto(Cursor cursor){
        id= cursor.getInt(cursor.getColumnIndex(ProductoEntry.ID));
        quantity= cursor.getInt( cursor.getColumnIndex( ProductoEntry.QUANTITY) );
        quantitys= cursor.getInt( cursor.getColumnIndex( ProductoEntry.QUANTITYS) );

    }

    public ContentValues toContentValues(){
        ContentValues values= new ContentValues();
        values.put( ProductoEntry.ID,id );
        values.put( ProductoEntry.QUANTITY,quantity );
        values.put( ProductoEntry.QUANTITYS,quantitys );
        return values;
    }



    public int getId() {

        return id;
    }

    public int getQuantity() {

        return quantity;
    }

    public int getQuantitys() {

        return quantitys;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
