#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>


// thread function to compute the product of two numbers
void *doubleProduct(void *arg) {
    double *values = (double *) arg;

    // i cant return an an array of double be cause it's a local var so i gotta allocate a pointer
    double *result = (double *) malloc(sizeof(double));


    *result = values[0] * values[1];

    printf("one of the computed values form the thread's is : %f\n", *result);

    return (void *) result;
}


int main(int argc, char *argv[]) {
    //  we get them from the terminal we pass them to the gcc
    double a = atof(argv[1]);
    double b = atof(argv[2]); double c = atof(argv[3]);


    double d = atof(argv[4]);

    double *result1; //  a * b
    double *result2; //  c * d


    double args1[] = {a, b};
    double args2[] = {c, d};


    pthread_t thread1, thread2;
    // a*b thread
    pthread_create(&thread1, NULL, doubleProduct, &args1);
    // c*d thread
    pthread_create(&thread2, NULL, doubleProduct, &args2);


    // waiting to merge the thread branches
    pthread_join(thread1, (void **) &result1);
    pthread_join(thread2, (void **) &result2);

    double final_result = *result1 + *result2;

    printf("product : %.2f\n", final_result);
    return 0;
}
