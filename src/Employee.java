
public class Employee extends Controller {
    private int id;
    private int branch;

    public Employee() {
    }

    public void employeeShowMenu() {
        int choice;
        boolean quit = false;

        while (!quit) {
            System.out.print("\n\nPlease choose one of the following: \n");
            System.out.print("1.  processPurchase\n");
            System.out.print("2.  manageMembership\n");
            System.out.print("3.  Quit\n");

            choice = Integer.parseInt(in.readLine());

            System.out.println(" ");

            switch (choice) {
                case 1:
                    processPurchase();
                    break;
                case 2:
                    manageMemberShip();
                    break;
                case 3:
                    quit = true;
            }
        }
    }

    private void manageMemberShip() {
        // TODO
        String name;
        String phone;
        int point;
        System.out.print("\nplease Enter Member name : \n");
        name = in.readLine();
        System.out.print("\nplease Enter Member phone number : \n");
        phone = in.readLine();
    }

    private void processPurchase() {
        // TODO
        int id;
        System.out.print("\nplease Enter item ID: \n");
        id = Integer.parseInt(in.readLine());
    }

    private void validateID() {
        int eid;
        PreparedStatement ps;
        ResultSet rs;
        boolean quit = false;

        while (!quit) {
            try {
                ps = con.prepareStatement("SELECT branchNumber FROM Clerk WHERE clerkID = ?");
                System.out.print("\nPlease enter your employee ID or press 0 to quit: ");
                eid = Integer.parseInt(in.readLine());
                if (eid == 0)
                    System.exit(0);
                ps.setInt(1, eid);

                rs = ps.executeQuery();
                if (rs == null)
                    System.out.print("\nAccess denied: Invalid employee ID");
                else {
                    this.branch = rs.getInt("branchNumber");
                    this.id = eid;
                    quit = true;
                }

                ps.close();

            } catch (IOException e) {
                System.out.println("IOException!");
            } catch (SQLException ex) {
                System.out.println("Message: " + ex.getMessage());

            }

        }

        employeeShowMenu();

    }
}