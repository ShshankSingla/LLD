public class Abstraction{
    public static void main(String[] args) {
        Car myCar = new SportsCar("Ford","Mustang");
        myCar.startEngine();
        myCar.shiftGear(1);
        myCar.accelerate();
        myCar.shiftGear(2);
        myCar.accelerate();
        myCar.brake();
        myCar.stopEngine();

    }
}

interface Car{

    // all methods are default -> public
    public void startEngine();
    public void shiftGear(int gear);
    public void accelerate();
    public void brake();
    public void stopEngine();


}

class SportsCar implements Car{
    String brand;
    String model;
    boolean isEngineOn;
    int currentSpeed;
    int currentGear;

    // constructor
    SportsCar(String brand, String model){
        this.brand = brand;
        this.model = model;
        isEngineOn = false;
        currentGear =0;
        currentSpeed =0;
    }


    // we we define logic of methods from interface use public
    @Override
    public void startEngine(){
        isEngineOn = true;
        System.out.println(brand + " " + model+" : Engine starts with a roar!");
    }

    @Override
    public void shiftGear(int gear){
        if(!isEngineOn){
            System.out.println(brand+" "+model+" : Engine is off! Can't Shift Gear.");
            return;
        }

        this.currentGear = gear;
        System.out.println(brand+" "+model+" : Shifted to gear "+currentGear);
    }

    @Override
    public void accelerate(){
        if(!isEngineOn){
            System.out.println(brand+" "+model+" : Engine is off! Can't Accelerate.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand+" "+model+" : Accelerating to "+currentSpeed+" km/h");
    }

    @Override
    public void brake(){
        currentSpeed -=20;
        if(currentSpeed<0) currentSpeed =0;
        System.out.println(brand+" "+model+" : Braking! speed is now "+currentSpeed+" km/h");
    }

    public void stopEngine(){
        isEngineOn = false;
        currentGear=0;
        currentSpeed =0;
        System.out.println(brand+" "+ model+" : Engine turned off.");
    }
}