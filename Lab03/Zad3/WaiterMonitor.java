// package Lab03.Zad3;

// public class WaiterMonitor {
//     final static PairsMonitor[] clientsReservations = new PairsMonitor[100]; // max 100 pairs
//     private int max;

//     public WaiterMonitor(int max) {
//         this.max = max;
//         for (int i = 0; i < max; i++) {
//             clientsReservations[i] = null;
//         }
//     }

//     public static void reserve(RestaurantClient restaurantClient) {
//         if (clientsReservations[(int) restaurantClient.getId()] == null) {
//             clientsReservations[(int) restaurantClient.getId()] = restaurantClient;
//         } else if (clientsReservations[(int) restaurantClient.getId()] != null) {

//         }
//     }

//     public static void release(RestaurantClient restaurantClient) {

//     }
// }
