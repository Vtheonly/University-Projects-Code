
public class Shape{
    Double r = 0.0;
    private String type;


    public Double area(Long length, Long breadth){
        double v = length * breadth;

        return v;
    }


    public Double area(Long radius){

        return (Double) 3.14 * r * r;
    }


    int main(){

        Shape n = new Shape();

        Double x = n.area(5L);

        

        return  0;
    }
}