// 1. Create Waiter, Chef, and Customer classes implementing the Runnable interface.
// 2. The Customer places an order, the Waiter picks it up and notifies the Chef.
// 3. The Chef prepares the order and notifies the Waiter to serve it.
// 4. Ensure thread synchronization using synchronized blocks or methods

package AssignmentFive;

class Order{
    String itemName;
    public Order(String itemName){
        this.itemName = itemName;
    }
}
class Waiter implements Runnable{
    Order currentOrder;
    public void run(){
        while (true){
            try{
                synchronized(this){
                    currentOrder = new Order("Pizza");
                    notify();
                    wait();
            }
            System.out.println("Waiter received Order: "+currentOrder);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }
}

class Chef implements Runnable{
    Waiter waiter;

    public Chef(Waiter waiter){
        this.waiter = waiter;
    }

    public void run(){
        while(true){
            try{
                synchronized(waiter){
                    waiter.wait();

                    System.out.println("Chef started preparing: "+waiter.currentOrder.itemName);
                    Thread.sleep(1000);
                    System.out.println("Chef finished prepating : "+waiter.currentOrder.itemName);

                    waiter.notify();
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Customer implements Runnable{
    Waiter waiter;

    public Customer(Waiter waiter){
        this.waiter = waiter;
    }

    public void run(){
        while(true){
            try {
                synchronized (waiter){
                    waiter.notify();
                    wait();
                }
                System.out.println("Customer placed order: Pizza");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
public class RestaurantOrderingSystem {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Chef chef = new Chef(waiter);
        Customer customer = new Customer(waiter);

        Thread waiterThread = new Thread(waiter);
        Thread chefThread = new Thread(chef);
        Thread customerThread = new Thread(customer);

        waiterThread.start();
        chefThread.start();
        customerThread.start();
    }
}
