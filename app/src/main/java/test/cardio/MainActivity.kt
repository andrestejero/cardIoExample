package test.cardio

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard
import kotlinx.android.synthetic.main.activity_main.*
import test.cardio.models.Item


class MainActivity : AppCompatActivity() {

    val MY_SCAN_REQUEST_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openRetrofit.setOnClickListener {
            LaunchRetrofit()
        }

        openKotlinTest.setOnClickListener {
            openKotlinTest();
        }

        openIoCard.setOnClickListener {
            openIoCard()
        }

        openExample.setOnClickListener {
            openExample()
        }

        openCustom.setOnClickListener {
            openCustom()
        }
    }

    fun openKotlinTest() {
        val item1 = Item(111, "Titulo 1", "url 1")
        val item2 = Item(222, "Titulo 2", "url 2")
        val item3 = Item(333, "Titulo 3", "url 3")
        val items = arrayListOf<Item>(item1, item2, item3)
        LaunchListActivity(items)
    }

    fun openExample() {
        val intent = Intent(this, SampleActivity::class.java)
        startActivity(intent)
    }

    fun openCustom() {
        val intent = Intent(this, CustomActivity::class.java)
        startActivity(intent)
    }

    fun openIoCard() {
        val scanIntent = Intent(this, CardIOActivity::class.java)

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            var resultDisplayStr: String
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                val scanResult = data.getParcelableExtra<CreditCard>(CardIOActivity.EXTRA_SCAN_RESULT)

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.redactedCardNumber + "\n"

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n"
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length + " digits.\n"
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n"
                }

            } else {
                resultDisplayStr = "Scan was canceled."
            }
            tvData.setText(resultDisplayStr);
        }
        // else handle other activity results
    }
}
