package com.uzair.graphswithindicatorsscreen;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends PagerAdapter {

    private Context context;
    private LineChart lineChart;
    private List<List<String>> xLables, yLables;
    private List<String> labelX = new ArrayList<>();
    private List<String> labelY = new ArrayList<>();
    private ArrayList lineEntries;
    private String[] xAxisLabels, yAxisLabels;


    public ViewpagerAdapter(Context context, List<List<String>> xLables,
                            List<List<String>> yLables, ArrayList lineEntries) {

        this.context = context;
        this.xLables = xLables;
        this.yLables = yLables;
        this.lineEntries = lineEntries;
    }

    @Override
    public int getCount() {
        return xLables.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View myView = LayoutInflater.from(context).inflate(R.layout.chart_item_for_view_pager, null);

        lineChart = myView.findViewById(R.id.linechart);


        labelY.clear();
        labelX.clear();
        lineChart.clear();

        for (int i = 0; i < xLables.size(); i++) {

            for (int j = 0; j < xLables.get(i).size(); j++) {
                labelX.add(xLables.get(i).get(j));
                labelY.add(yLables.get(i).get(j));


                LineData data = new LineData();

                List<ILineDataSet> dataSets = new ArrayList<>();
                LineDataSet bds = new LineDataSet(lineEntries, " ");
                bds.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                bds.setColors(context.getResources().getColor(R.color.active_color));
                bds.setValueTextColor(Color.BLACK);
                bds.setLineWidth(3f);

                xAxisLabels = labelX.toArray(new String[0]);
                yAxisLabels = labelY.toArray(new String[0]);
                dataSets.add(bds);
                data.addDataSet(bds);
                data.setDrawValues(false);


                XAxis xaxis = lineChart.getXAxis();
                xaxis.setDrawGridLines(false);
                xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xaxis.setTextSize(12f);
                xaxis.setGranularityEnabled(false);
                xaxis.setGranularity(1);
                xaxis.setDrawLabels(true);
                xaxis.setDrawAxisLine(false);
                CategoryBarChartXaxisFormatter xaxisFormatter = new CategoryBarChartXaxisFormatter(xAxisLabels);
                xaxis.setValueFormatter(xaxisFormatter);

                YAxis yAxisLeft = lineChart.getAxisRight();
                yAxisLeft.setEnabled(false);

                YAxis yAxisRight = lineChart.getAxisLeft();
                yAxisRight.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
                yAxisRight.setDrawGridLines(true);
                yAxisRight.setDrawAxisLine(false);
                yAxisRight.setDrawLabels(true);

                CategoryBarChartXaxisFormatter yaxisFormatter = new CategoryBarChartXaxisFormatter(yAxisLabels);
                yAxisRight.setValueFormatter(yaxisFormatter);

                Legend legend = lineChart.getLegend();
                legend.setEnabled(false);

                lineChart.setData(data);
                lineChart.setExtraBottomOffset(14f);
                lineChart.setExtraRightOffset(20f);
                lineChart.setDescription(null);

            }
        }

        container.addView(myView);

        return myView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


    public class CategoryBarChartXaxisFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public CategoryBarChartXaxisFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {

            int val = (int) value;
            String label = "";
            if (val >= 0 && val < mValues.length) {
                label = mValues[val];
            } else {
                label = "";
            }
            return label;
        }

    }

}
