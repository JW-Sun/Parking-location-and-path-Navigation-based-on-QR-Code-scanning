package kongjiandongtai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity33 extends AppCompatActivity {
    private List<List<Integer>> mData;
    private int[] mRes = new int[] {R.drawable.bg_imv, R.drawable.android, R.drawable.caozuoxt, R.drawable.jiwang,
            R.drawable.jizu, R.drawable.jxl, R.drawable.library, R.drawable.library2,
            R.drawable.sjjg, R.drawable.northdoor, R.drawable.user1, R.drawable.user2,
            R.drawable.bg_imv, R.drawable.bg_imv, R.drawable.bg_imv};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);
        ListView lsvGrid = (ListView) findViewById(R.id.lsv_grid);
        mData = loadTestData();
        lsvGrid.setAdapter(new GridTestAdapter());
    }

    private List<List<Integer>> loadTestData() {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int i=0; i<100; i++) {
            List<Integer> item = new ArrayList<Integer>();
            int count = new Random().nextInt(10) + 1;
            Log.e("ZLH", ""+count);
            for(int j=0; j<count; j++) {
                item.add(mRes[new Random().nextInt(mRes.length-1) + 1]);
            }
            result.add(item);
        }

        return result;
    }

    private class GridTestAdapter extends BaseAdapter {
        public GridTestAdapter() {
        }
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(null == convertView) {
                convertView = LayoutInflater.from(MainActivity33.this).inflate(R.layout.item_grid, null);
                holder = new ViewHolder();
                holder.grdItem = (GridListView) convertView.findViewById(R.id.grd_item);
                holder.txvNum = (TextView) convertView.findViewById(R.id.txv_num);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            List<Integer> data = mData.get(position);
            holder.grdItem.setData(data);
            holder.txvNum.setText("图片数：" + data.size());

            return convertView;
        }
        class ViewHolder {
            TextView txvNum;
            GridListView grdItem;
        }
    }
}
