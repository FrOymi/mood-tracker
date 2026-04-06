import services.DateTimeManagement;

import objects.*;
import services.*;

class MoodTest {
    public static void main(String[] args) {
        Time actualTime = new Time();
        DateTimeManagement.syncTime(actualTime);
        System.out.println(actualTime);
    }
}
