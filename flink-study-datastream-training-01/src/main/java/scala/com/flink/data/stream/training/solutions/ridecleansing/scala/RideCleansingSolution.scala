package scala.com.flink.data.stream.training.solutions.ridecleansing.scala

import org.apache.flink.streaming.api.scala._
import org.apache.flink.training.exercises.common.sources.TaxiRideGenerator
import org.apache.flink.training.exercises.common.utils.ExerciseBase._
import org.apache.flink.training.exercises.common.utils.{ExerciseBase, GeoUtils}

/**
 *
 * @author Jack House
 * @date 2021年03月21日 18:13:07
 */
object RideCleansingSolution {

  def main(args: Array[String]): Unit = {

    //设置执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置并行任务数
    env.setParallelism(ExerciseBase.parallelism)

    //获取数据源
    val rides = env.addSource(rideSourceOrTest(new TaxiRideGenerator()))

    //过滤数据流
    val filteredRides = rides.filter(r => GeoUtils.isInNYC(r.startLon, r.startLat) && GeoUtils.isInNYC(r.endLon, r.endLat))

    //打印过滤后的数据流
    printOrScalaTest(filteredRides)

    //执行flink应用
    env.execute("Taxi Ride Cleansing")

  }

}
