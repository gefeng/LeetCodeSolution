package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Traffic Light Controlled Intersection",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/traffic-light-controlled-intersection/"
)
public class Q1279 {
    private Integer signal;
    public Q1279() {
        signal = 1;
    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {

        synchronized(signal) {
            if(signal != roadId) {
                signal = roadId;
                turnGreen.run();
            }
            crossCar.run();
        }
    }
}
