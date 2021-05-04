package sg.edu.rp.c346.id19044628.l03_billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    EditText amt;
    EditText pax;
    EditText discount;
    ToggleButton svs;
    ToggleButton gst;
    RadioGroup rgPayment;
    Button split;
    Button reset;
    TextView total;
    TextView each;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt= findViewById(R.id.editTextAmt);
        pax= findViewById(R.id.editTextPax);
        discount= findViewById(R.id.editTextDiscount);
        svs= findViewById(R.id.togglebuttonService);
        gst= findViewById(R.id.togglebuttonGst);
        rgPayment=findViewById(R.id.rgPayment);
        split= findViewById(R.id.buttonSplit);
        reset= findViewById(R.id.buttonReset);
        total= findViewById(R.id.textViewTotalBill);
        each= findViewById(R.id.textViewEachPay);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amt.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0) {
                    double origAmt = Double.parseDouble(amt.getText().toString());
                    double newAmt = 0.0;

                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = origAmt;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = origAmt * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = origAmt * 1.07;

                    } else {
                        newAmt = origAmt * 1.17;
                    }

                    if (discount.getText().toString().trim().length() != 0)
                    {
                        newAmt*= 1- Double.parseDouble(discount.getText().toString())/100;
                    }

                    String mode=" in cash";
                    if(rgPayment.getCheckedRadioButtonId() == R.id.radioButtonPayNow)
                    {
                        mode= " via PayNow to 912345678";
                    }

                    total.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPax= Integer.parseInt(pax.getText().toString());
                    if(numPax !=1)
                    {
                        each.setText("Each Pays: $" + String.format("%.2f", newAmt/numPax)+mode);
                    }
                    else
                    {
                        each.setText("Each Pays: $" + newAmt+mode);
                    }


                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                rgPayment.check(R.id.radioButtonCash);
                total.setText("");
                each.setText("");
            }
        });
    }
}