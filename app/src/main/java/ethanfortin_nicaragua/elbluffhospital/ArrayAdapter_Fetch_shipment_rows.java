package ethanfortin_nicaragua.elbluffhospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Wildcat on 1/3/2017.
 */

public class ArrayAdapter_Fetch_shipment_rows extends ArrayAdapter<Class_Fetch_shipment_rows> {
    public ArrayAdapter_Fetch_shipment_rows(Context context, ArrayList<Class_Fetch_shipment_rows> drugdata){
        super(context,0,drugdata);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Class_Fetch_shipment_rows single_druginfo = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_fetch_shipments_rows, parent, false);
        }

        TextView drugid = (TextView)convertView.findViewById(R.id.drugid);
        TextView drugname = (TextView)convertView.findViewById(R.id.drugname);
        TextView shipquant = (TextView)convertView.findViewById(R.id.shipquant);
        TextView shipdate = (TextView)convertView.findViewById(R.id.shipdate) ;

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String date = sdf.format(single_druginfo.shipdate);

        drugid.setText(single_druginfo.drugid);
        drugname.setText(single_druginfo.drugname);
        shipquant.setText(Integer.toString(single_druginfo.shipquant));
        shipdate.setText(date);

        /*
        Still need to figure out this date thing
        SimpleDateFormat df = new SimpleDateFortmat("dd/MM/yyyy");
        shipdate.setText(df.format(single_druginfo.shipdate));*/


        return convertView;

    }

}
