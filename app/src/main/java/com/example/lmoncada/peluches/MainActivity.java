package com.example.lmoncada.peluches;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


        EditText id, nombre, cantidad, contenido, valor ;
        Button agregar, modificar, eliminar, ver, limpiar, buscar, venta, ganancias;
        Integer contPeluches=0;
        private static final String FIREBASE_URL="https://mispeluchesluisa.firebaseIO.com";
        private Firebase firebasedatos;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            id = (EditText) findViewById(R.id.eId);
            nombre = (EditText) findViewById(R.id.eNombre);
            cantidad = (EditText) findViewById(R.id.eCantidad);
            contenido = (EditText) findViewById(R.id.eContenido);
            valor = (EditText) findViewById(R.id.eValor);
            agregar = (Button) findViewById(R.id.bAgregar);
            modificar = (Button) findViewById(R.id.bModificar);
            eliminar = (Button) findViewById(R.id.bEliminar);
            ver = (Button) findViewById(R.id.bInventario);
            limpiar = (Button) findViewById(R.id.bLimpiar);
            buscar = (Button) findViewById(R.id.bBuscar);
            venta = (Button) findViewById(R.id.bVenta);
            ganancias = (Button) findViewById(R.id.bGanancias);

            Firebase.setAndroidContext(this);
            firebasedatos = new Firebase(FIREBASE_URL);

            agregar.setOnClickListener(this);
            modificar.setOnClickListener(this);
            eliminar.setOnClickListener(this);
            ver.setOnClickListener(this);
            limpiar.setOnClickListener(this);
            buscar.setOnClickListener(this);
            venta.setOnClickListener(this);
            ganancias.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.bLimpiar:
                    id.getText().clear();
                    nombre.getText().clear();
                    cantidad.getText().clear();
                    contenido.getText().clear();
                    valor.getText().clear();

                    break;

                case R.id.bAgregar:
                    Toast.makeText(MainActivity.this, "Agregar", Toast.LENGTH_SHORT).show();
                    String name = nombre.getText().toString();
                    String quantity = cantidad.getText().toString();
                    String price = valor.getText().toString();
                    String sale = cantidad.getText().toString();
                    String gain = ganancias.getText().toString();

                    Firebase firebd = firebasedatos.child("Peluche " + contPeluches);
                    Contactos peluches = new Contactos(String.valueOf(contPeluches), name, quantity, price, sale);
                    firebd.setValue(peluches);

                    id.getText().clear();
                    nombre.getText().clear();
                    cantidad.getText().clear();
                    valor.getText().clear();
                    contPeluches++;

                    break;

                case R.id.bInventario:
                    firebasedatos.addValueEventListener(new ValueEventListener() {

                        public void onDataChange(DataSnapshot dataSnapshot) {
                            contenido.setText(dataSnapshot.getValue().toString());
                        }

                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                    break;

                case  R.id.bBuscar:
                    String codigo = id.getText().toString();
                    final String idS = "Peluche"+codigo;
                    firebasedatos.addValueEventListener(new ValueEventListener() {


                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(idS).exists()){
                                contenido.setText(dataSnapshot.child(idS).getValue().toString());
                            }
                        }

                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                    break;

                case R.id.bModificar: {
                    codigo = id.getText().toString();
                    name = nombre.getText().toString();
                    quantity = cantidad.getText().toString();
                    price = valor.getText().toString();

                    firebd = firebasedatos.child("Peluche" + codigo);
                    Map<String, Object> nuevoNombre = new HashMap<>();
                    nuevoNombre.put("nombre", name);
                    firebd.updateChildren(nuevoNombre);

                    Map<String, Object> nuevoCantidad = new HashMap<>();
                    nuevoCantidad.put("cantidad", quantity);
                    firebd.updateChildren(nuevoCantidad);


                    Map<String, Object> nuevoValor = new HashMap<>();
                    nuevoValor.put("valor", price);
                    firebd.updateChildren(nuevoValor);

                    id.getText().clear();
                    nombre.getText().clear();
                    cantidad.getText().clear();
                    valor.getText().clear();
                }
                break;

                case R.id.bEliminar: {
                    codigo = id.getText().toString();
                    firebd = firebasedatos.child("Peluche" + codigo);
                    firebd.removeValue();
                }
                break;


                case R.id.bVenta: {

                    codigo = id.getText().toString();
                    name = nombre.getText().toString();
                    quantity = cantidad.getText().toString();
                    price = valor.getText().toString();
                    sale = venta.getText().toString();

                    firebd = firebasedatos.child("Peluche" + codigo);
                    Map<String, Object> nuevoNombre = new HashMap<>();
                    nuevoNombre.put("nombre", name);
                    firebd.updateChildren(nuevoNombre);

                    Map<String, Object> nuevaVenta = new HashMap<>();
                    nuevaVenta.put("Venta",sale);
                    firebd.updateChildren(nuevaVenta);

                    Map<String, Object> nuevoValor = new HashMap<>();
                    nuevoValor.put("valor", price);
                    firebd.updateChildren(nuevoValor);

                    id.getText().clear();
                    nombre.getText().clear();
                    cantidad.getText().clear();
                    valor.getText().clear();
                }
                break;

                case R.id.bGanancias: {

                    codigo = id.getText().toString();
                    name = nombre.getText().toString();
                    quantity = cantidad.getText().toString();
                    price = valor.getText().toString();
                    sale = venta.getText().toString();
                    gain = ganancias.getText().toString();

                    firebd = firebasedatos.child("Peluche" + codigo);
                    Map<String, Object> nuevoNombre = new HashMap<>();
                    nuevoNombre.put("nombre", name);
                    firebd.updateChildren(nuevoNombre);

                    Map<String, Object> nuevoCantidad = new HashMap<>();
                    nuevoCantidad.put("cantidad", quantity);
                    firebd.updateChildren(nuevoCantidad);

                    Map<String, Object> nuevoValor = new HashMap<>();
                    nuevoValor.put("valor", price);
                    firebd.updateChildren(nuevoValor);

                    Map<String, Object> toralGanancias = new HashMap<>();
                    toralGanancias.put("Ganancias",gain);
                    firebd.updateChildren(toralGanancias);


                    id.getText().clear();
                    nombre.getText().clear();
                    cantidad.getText().clear();
                    valor.getText().clear();
                }

            }
        }
    }

