package com.flink.data.stream.training.solutions.ridecleansing;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.training.exercises.common.datatypes.TaxiRide;
import org.apache.flink.training.exercises.common.sources.TaxiRideGenerator;
import org.apache.flink.training.exercises.common.utils.ExerciseBase;
import org.apache.flink.training.exercises.common.utils.GeoUtils;

/**
 * @author Jack House
 * @date 2021年03月21日 17:13:44
 */
public class RideCleansingSolution extends ExerciseBase {

    public static void main(String[] args) throws Exception {

        //初始化Flink的Streaming（流计算）上下文执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //将 job或运算符的并行度设置为x，即运算符的并行任务数。
        env.setParallelism(ExerciseBase.parallelism);

        //添加数据源，并将数据组装成数据流
        DataStream<TaxiRide> rides = env.addSource(rideSourceOrTest(new TaxiRideGenerator()));
        //过滤数据，并返回过滤后的数据流
        DataStream<TaxiRide> filteredRides = rides.filter(new NYCFilter());
        //打印过滤后的数据流
        printOrTest(filteredRides);
        //执行flink应用
        env.execute("Taxi Ride Cleansing");
    }

    /**
     * 纽约过滤器类
     */
    public static class NYCFilter implements FilterFunction<TaxiRide> {

        /**
         *
         * @param taxiRide 传入坐标
         * @return boolean true 在纽约内 false 在纽约之外
         * @throws Exception
         */
        @Override
        public boolean filter(TaxiRide taxiRide) throws Exception {
            return GeoUtils.isInNYC(taxiRide.startLon,taxiRide.startLat) && GeoUtils.isInNYC(taxiRide.endLon, taxiRide.endLat);
        }
    }
}
