package com.uzair.graphswithindicatorsscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<List<String>> xLables, yLables;
    private ArrayList<Entry> lineEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager();
        setBarChartOfConversion();
        setLineChartOfImpression();
    }

    private void setViewPager() {
        xLables = new ArrayList<>();
        yLables = new ArrayList<>();
        List<String> xFirst = new ArrayList<>();
        xFirst.add("Rice");
        xFirst.add("Sugary");
        xFirst.add("Coffee");
        xFirst.add("Grains");
        xFirst.add("Wheat");
        xFirst.add("sweet");
        xLables.add(xFirst);
        List<String> xSecond = new ArrayList<>();
        xFirst.add("Rice");
        xFirst.add("Sugary");
        xFirst.add("Coffee");
        xFirst.add("Grains");
        xFirst.add("Wheat");
        xFirst.add("sweet");
        xLables.add(xSecond);
        List<String> xThird = new ArrayList<>();
        xFirst.add("Rice");
        xFirst.add("Sugary");
        xFirst.add("Coffee");
        xFirst.add("Grains");
        xFirst.add("Wheat");
        xFirst.add("sweet");
        xLables.add(xThird);

        List<String> yFirst = new ArrayList<>();
        yFirst.add("$0");
        yFirst.add("$20");
        yFirst.add("$40");
        yFirst.add("$60");
        yFirst.add("$80");
        yFirst.add("$90");
        yLables.add(yFirst);
        List<String> ySecond = new ArrayList<>();
        yFirst.add("$0");
        yFirst.add("$20");
        yFirst.add("$40");
        yFirst.add("$60");
        yFirst.add("$80");
        yFirst.add("$90");
        yLables.add(ySecond);
        List<String> yThird = new ArrayList<>();
        yFirst.add("$0");
        yFirst.add("$20");
        yFirst.add("$40");
        yFirst.add("$60");
        yFirst.add("$80");
        yFirst.add("$90");
        yLables.add(yThird);


        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(0f, 1));
        lineEntries.add(new Entry(1f, 3));
        lineEntries.add(new Entry(2f, 5));
        lineEntries.add(new Entry(3f, 2));
        lineEntries.add(new Entry(4f, 4));
        lineEntries.add(new Entry(5f, 3));

        ViewPager viewPager = findViewById(R.id.photos_viewpager);

        ViewpagerAdapter adapter = new ViewpagerAdapter(this, xLables, yLables, lineEntries);
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin(20);

        addDot(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void addDot(int page_position) {

        LinearLayout layout_dot = (LinearLayout) findViewById(R.id.layout);

        TextView[] dot = new TextView[xLables.size()];
        layout_dot.removeAllViews();

        for (int i = 0; i < dot.length; i++) {
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#9679;"));
            dot[i].setTextSize(10);
            //set default dot color
            dot[i].setTextColor(getResources().getColor(R.color.non_active_color));
           // layout_dot.addView(dot[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(6, 0, 6, 0);
            layout_dot.addView(dot[i], params);

        }
        //set active dot color
        dot[page_position].setTextColor(getResources().getColor(R.color.active_color));

    }

    private void setBarChartOfConversion()
    {
        BarChart barChart = findViewById(R.id.barChart);
        BarData data = new BarData();

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        valueSet1.add(new BarEntry(0f, 30));
        valueSet1.add(new BarEntry(1f, 50));
        valueSet1.add(new BarEntry(2f, 100));
        valueSet1.add(new BarEntry(3f, 150));
        valueSet1.add(new BarEntry(4f, 200));
        valueSet1.add(new BarEntry(5f, 250));

        List<IBarDataSet> dataSets = new ArrayList<>();
        BarDataSet bds = new BarDataSet(valueSet1, " ");
        bds.setColors(getResources().getColor(R.color.active_color));
        bds.setBarBorderColor(getResources().getColor(R.color.active_color));
        dataSets.add(bds);
        data.addDataSet(bds);
        data.setDrawValues(false);
        data.setBarWidth(0.9f);

        XAxis xaxis = barChart.getXAxis();
        xaxis.setDrawGridLines(false);
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setGranularityEnabled(false);
        xaxis.setGranularity(1);
        xaxis.setDrawLabels(false);
        xaxis.setDrawAxisLine(false);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setEnabled(false);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxisLeft.setDrawAxisLine(false);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        barChart.setFitBars(false);
        barChart.setData(data);
        barChart.setDescription(null);


    }


    private void setLineChartOfImpression()
    {
       LineChart lineChart = findViewById(R.id.impressionLineChart);
        LineData data = new LineData();

        List<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet bds = new LineDataSet(lineEntries, " ");
        bds.setDrawValues(false);
        bds.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        bds.setColors(getResources().getColor(R.color.active_color));
        bds.setValueTextColor(Color.BLACK);
        bds.setLineWidth(2f);
        dataSets.add(bds);
        data.addDataSet(bds);
        data.setDrawValues(false);


        XAxis xaxis = lineChart.getXAxis();
        xaxis.setDrawGridLines(false);
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setTextSize(12f);
        xaxis.setGranularityEnabled(false);
        xaxis.setGranularity(1);
        xaxis.setDrawLabels(false);
        xaxis.setDrawAxisLine(false);

        YAxis yAxisLeft = lineChart.getAxisRight();
        yAxisLeft.setEnabled(false);

        YAxis yAxisRight = lineChart.getAxisLeft();
        yAxisRight.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        lineChart.setData(data);
        lineChart.setDescription(null);

    }

}
