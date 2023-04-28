package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int num_of_rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int num_of_seats = scanner.nextInt();

        // array to store cinema seats
        char[][] cinema = new char[num_of_rows + 1][num_of_seats + 1];
        for (int i = 1; i <= num_of_rows; i++) {
            for (int j = 1; j <= num_of_seats; j++) {
                cinema[i][j] = 'S';
            }
        }

        // number of tickets purchased
        int purchased_tickets = 0;
        int total_seats = num_of_seats * num_of_rows;
        int current_income = 0;
        int total_income = 0;

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:
                    showSeats(cinema, num_of_rows, num_of_seats);
                    break;
                case 2:
                    do {
                        System.out.println("Enter a row number:");
                        int row_num = scanner.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        int seat_num = scanner.nextInt();

                        if (row_num > num_of_rows || seat_num > num_of_seats) {
                            System.out.println("Wrong input!");
                        } else {
                            if (cinema[row_num][seat_num] == 'B') {
                                System.out.println("That ticket has already been purchased!");
                            } else {
                                cinema[row_num][seat_num] = 'B';
                                int ticket_price = buyTicket(num_of_rows, num_of_seats,
                                        row_num);
                                purchased_tickets += 1;
                                System.out.println("Ticket price: $" + ticket_price);
                                current_income += ticket_price;
                                break;
                            }
                        }
                    } while (true);
                case 3:
                    float percentage_of_tickets = (float) purchased_tickets / total_seats * 100;
                    if (total_seats <= 60) {
                        total_income = total_seats * 10;
                    } else {
                        int half_row = num_of_rows / 2;
                        int half_row_price = half_row * num_of_seats* 10;
                        int rest_row = num_of_rows - half_row;
                        int rest_row_prices = rest_row * num_of_seats * 8;
                        total_income = half_row_price + rest_row_prices;
                    }

                    System.out.println("Number of purchased tickets: " + purchased_tickets);
                    System.out.printf("Percentage: %.2f%%", percentage_of_tickets);
                    System.out.println();
                    System.out.println("Current income: $" + current_income);
                    System.out.println("Total income: $" + total_income);
                    break;
            }
        }
    }

    // function to buy a ticket
    public static int buyTicket(int num_of_rows, int num_of_seats,int row_num) {
        int half_row = num_of_rows / 2;
        int ticket_prices;
        int total_seat = num_of_seats * num_of_rows;

        if (total_seat > 60) {
            if (row_num <= half_row) {
                ticket_prices = 10;
            } else {
                ticket_prices = 8;
            }
        } else {
            ticket_prices = 10;
        }
        return ticket_prices;
    }

    //function to show the seats
    public static void showSeats(char[][] cinema, int num_rows, int num_seats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= num_seats; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for (int i = 1; i <= num_rows; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= num_seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        return;
    }
}