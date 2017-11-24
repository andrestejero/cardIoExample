package test.cardio;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class CustomActivity extends Activity {

    private static final String LOG_TAG = CustomActivity.class.getSimpleName();

    private static final int MY_SCAN_REQUEST_CODE = 2017;

    private TextView cardNumber;
    private TextView expirationDate;
    private ImageView mResultImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);

        cardNumber = (TextView) findViewById(R.id.cardNumber);
        expirationDate = (TextView) findViewById(R.id.expirationDate);
        mResultImage = (ImageView) findViewById(R.id.result_image);

        Intent intent = new Intent(this, CardIOActivity.class);

        intent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true);
        intent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);
        intent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, true);
        intent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
        intent.putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true);
        intent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        intent.putExtra(CardIOActivity.EXTRA_SCAN_INSTRUCTIONS, "Colocá tu tarjeta dentro del marco");

        startActivityForResult(intent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String outStr = "";

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                if (scanResult != null) {
                    outStr += "Card number: " + scanResult.getRedactedCardNumber() + "\n";
                    cardNumber.setText(scanResult.getFormattedCardNumber());
                    expirationDate.setText(scanResult.expiryMonth + " - " + scanResult.expiryYear);
                }

            } else {
                Log.d(LOG_TAG, "Scan was canceled.");
            }

            Bitmap card = CardIOActivity.getCapturedCardImage(data);
            mResultImage.setImageBitmap(card);


            Log.i(LOG_TAG, "Set result: " + outStr);
        }
    }
}
