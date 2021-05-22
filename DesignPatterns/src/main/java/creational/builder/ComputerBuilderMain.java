package creational.builder;

import java.time.LocalDate;

public class ComputerBuilderMain{
    public static void main(String[] args) {
        Computer.ComputerBuilder builder =new Computer.ComputerBuilder();
        builder.setName("MAC").setCreatedOn(LocalDate.now());
        System.out.println(builder.build());
    }
}
class Computer {
    private String name;
    private String ram;
    private String company;
    private LocalDate createdOn;

    private Computer(ComputerBuilder builder){
        this.name = builder.name;
        this.ram = builder.ram;
        this.company = builder.company;
        this.createdOn = builder.createdOn;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", ram='" + ram + '\'' +
                ", company='" + company + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }

    public static class ComputerBuilder{

        private LocalDate createdOn;
        private String company;
        private String ram;
        private String name;

        public ComputerBuilder setCreatedOn(LocalDate createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public ComputerBuilder setCompany(String company) {
            this.company = company;
            return this;
        }

        public ComputerBuilder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public ComputerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }
}
