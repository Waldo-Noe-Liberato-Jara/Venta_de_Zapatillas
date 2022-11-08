package com.liberato.tarea_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner_Marca;
    private Spinner spinner_Talla;
    private EditText texto_Marca;
    private EditText texto_Talla;
    private EditText texto_Pares_vendidos;
    private TextView texto_Mensaje;
    private String[] Marcas = {"Nike", "Adidas", "Filas"};
    private String[] Tallas = {"38", "40", "42"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Insertando items a los spinners */
        spinner_Marca = (Spinner) findViewById(R.id.spnMarca);
        spinner_Talla = (Spinner) findViewById(R.id.spnTalla);

        ArrayAdapter<String> Adaptador_Marcas = new ArrayAdapter<>(this, R.layout.spinner_items_zapatillas, Marcas);
        ArrayAdapter<String> Adaptador_Tallas = new ArrayAdapter<>(this, R.layout.spinner_items_zapatillas, Tallas);

        spinner_Marca.setAdapter(Adaptador_Marcas);
        spinner_Talla.setAdapter(Adaptador_Tallas);
        Toast.makeText(this, "Bienvenid@", Toast.LENGTH_LONG).show();
    }

    public void MostrarMarcaSeleccionada(View v) {
        /* Obteniendo el valor del spinner de Marca*/
        try {
            int positionMarca = spinner_Marca.getSelectedItemPosition();
            String respuesta_marca = Marcas[positionMarca].toString();

            /* Obteniendo el valor del spinner de Talla*/
            int positionTalla = spinner_Talla.getSelectedItemPosition();
            String respuesta_talla = Tallas[positionTalla].toString();

            /*  PROCESO de la aplicación*/
            texto_Pares_vendidos = (EditText) findViewById(R.id.txtPares_vendidos);
            int pares_vendidos = Integer.parseInt(texto_Pares_vendidos.getText().toString());


            /* MENSAJE de factura de zapatilla */
            texto_Pares_vendidos = (EditText) findViewById(R.id.txtPares_vendidos);
            texto_Mensaje = (TextView) findViewById(R.id.txtMensaje);

            texto_Mensaje.setText("Producto comprado: " + respuesta_marca + "\nTalla: " + respuesta_talla + "\nCantidad de pares vendidos: " + texto_Pares_vendidos.getText().toString() + "\nPrecio neto: S/." + Precio_Total(positionTalla, positionMarca)[0] + "\nDescuento de " + Precio_Total(positionTalla, positionMarca)[1] + "% es: S/." + Precio_Total(positionTalla, positionMarca)[2] + "\nPrecio total: S/." + Precio_Total(positionTalla, positionMarca)[3]);
        } catch (Exception e) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    private Double[] Precio_Total(int posicion_de_talla, int posicion_de_marca) {

        Double precio_por_talla = 0.0;
        Double[] Talla_38 = {150.0, 140.0, 80.0};
        Double[] Talla_40 = {160.0, 150.0, 85.0};
        Double[] Talla_42 = {160.0, 150.0, 90.0};

        if (posicion_de_talla == 0 && posicion_de_marca == 0) {
            precio_por_talla = Talla_38[0];
        } else if (posicion_de_talla == 1 && posicion_de_marca == 0) {
            precio_por_talla = Talla_40[0];
        } else if (posicion_de_talla == 2 && posicion_de_marca == 0) {
            precio_por_talla = Talla_42[0];
        } else if (posicion_de_talla == 0 && posicion_de_marca == 1) {
            precio_por_talla = Talla_38[1];
        } else if (posicion_de_talla == 1 && posicion_de_marca == 1) {
            precio_por_talla = Talla_40[1];
        } else if (posicion_de_talla == 2 && posicion_de_marca == 1) {
            precio_por_talla = Talla_42[1];
        } else if (posicion_de_talla == 0 && posicion_de_marca == 2) {
            precio_por_talla = Talla_38[2];
        } else if (posicion_de_talla == 1 && posicion_de_marca == 2) {
            precio_por_talla = Talla_40[2];
        } else if (posicion_de_talla == 2 && posicion_de_marca == 2) {
            precio_por_talla = Talla_42[2];
        }

        int cantidad_de_pares = Integer.parseInt(texto_Pares_vendidos.getText().toString());
        Double total_neto = precio_por_talla * cantidad_de_pares;
        Double descuento_aplicado = total_neto * DescuentoPorCantidad(cantidad_de_pares);
        Double total_De_compra = total_neto - descuento_aplicado;

        Double[] Neto_Descuento_Total = {total_neto, DescuentoPorCantidad(cantidad_de_pares), descuento_aplicado, total_De_compra};

        return Neto_Descuento_Total;
    }

    private Double DescuentoPorCantidad(int cantidad_de_pares) {
        Double Descuento = 0.0;

        try {
            Double[] descuentos = {0.05, 0.08, 0.10, 0.15};

            if (cantidad_de_pares >= 2 && cantidad_de_pares <= 5) {
                Descuento = descuentos[0];
            } else if (cantidad_de_pares >= 6 && cantidad_de_pares <= 10) {
                Descuento = descuentos[1];
            } else if (cantidad_de_pares >= 11 && cantidad_de_pares <= 20) {
                Descuento = descuentos[2];
            } else if (cantidad_de_pares > 20) {
                Descuento = descuentos[3];
            }
        } catch (Exception e) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show();
        }

        return Descuento;

    }
}