package intents.example.net.intents;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button web = (Button)findViewById(R.id.button_web);
        Button telefono = (Button)findViewById(R.id.button_telefono);
        Button maps = (Button)findViewById(R.id.button_maps);
        Button foto = (Button)findViewById(R.id.button_foto);
        Button correo = (Button)findViewById(R.id.button_correo);
        Button busqueda = (Button)findViewById(R.id.button_busqueda);
        Button dialer = (Button)findViewById(R.id.button_dialer);
        Button street = (Button)findViewById(R.id.button_street);
        Button compartir = (Button)findViewById(R.id.button_compartir);

        web.setOnClickListener(this);
        telefono.setOnClickListener(this);
        maps.setOnClickListener(this);
        foto.setOnClickListener(this);
        correo.setOnClickListener(this);
        busqueda.setOnClickListener(this);
        dialer.setOnClickListener(this);
        street.setOnClickListener(this);
        compartir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_web:
                pgWeb(v);
                break;
            case R.id.button_telefono:
                llamadaTelefono(v);
                break;
            case R.id.button_maps:
                googleMaps(v);
                break;
            case R.id.button_foto:
                hacerFoto(v);
                break;
            case R.id.button_correo:
                mandarCorreo(v);
                break;
            case R.id.button_busqueda:
                lanzarBusqueda(v);
                break;
            case R.id.button_dialer:
                lanzarDialer(v);
                break;
            case R.id.button_street:
                lanzarStreet(v);
                break;
            case R.id.button_compartir:
                lanzarCompartir(v);
                break;
        }
    }
    public void pgWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://campus.somtic.net/"));
        startActivity(intent);
    }
    public void llamadaTelefono(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:966870700"));
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:966870700"));
            startActivity(intent);
        }
    }
    public void googleMaps(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:38.553468,-0.121579"));
        startActivity(intent);
    }
    public void hacerFoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"smira@iesperemaria.com" });
        startActivity(intent);
    }
    public void lanzarBusqueda(View view){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
        intent.putExtra(SearchManager.QUERY, "IES Pere maria");
    }
    public void lanzarDialer(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:966870700"));
        startActivity(intent);
    }
    public void lanzarStreet(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=38.553468,-0.121579"));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
    public void lanzarCompartir(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Compartido desde IES Pere Maria Orts!!");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"Compartir con ..."));
    }
}
