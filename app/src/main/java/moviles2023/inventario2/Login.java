package moviles2023.inventario2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import moviles2023.inventario2.data.InventarioDBHelper;
import moviles2023.inventario2.data.Producto;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {


    private EditText idProducto;
    private EditText quantity;
    private EditText quantitys;
    private Button loginBoton;
    private Button registrarBoton;

    private InventarioDBHelper baseDatos;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        idProducto=(EditText) getView().findViewById( R.id.idProducto );
        quantity= (EditText) getView().findViewById( R.id.quantity);
        quantitys= (EditText) getView().findViewById( R.id.quantitys);
        loginBoton = (Button) getView().findViewById( R.id.loginButton ) ;
        registrarBoton = (Button) getView().findViewById( R.id.registerButton);

        baseDatos = new InventarioDBHelper( getContext() );

        loginBoton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor productoConsultado =baseDatos.getProductoById( idProducto.getText().toString() );
                if(productoConsultado.moveToFirst()){
                    Navigation.findNavController(view).navigate(R.id.menuPrincipalV2);
                }else{
                    Toast.makeText( getActivity(),"Datos incorrectos del producto",Toast.LENGTH_LONG ).show();
                }
            }
        } );

        registrarBoton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idValue = Integer.parseInt(idProducto.getText().toString());
                int quantityValue = Integer.parseInt(quantity.getText().toString());
                int quantitysValue= Integer.parseInt(quantitys.getText().toString());
                Producto productoNuevo = new Producto(idValue,quantityValue,quantitysValue);
                baseDatos.saveProducto(productoNuevo );
            }
        } );


    }
}
