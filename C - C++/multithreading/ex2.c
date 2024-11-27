#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

struct args {
    double a;
    double b;
    double *result;
};

void *multiply(void *arg) {
    struct args *args = (struct args *)arg;
    *(args->result) = args->a * args->b;
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    if (argc != 5) {
        printf("Usage: %s a b c d\n", argv[0]);
        return 1;
    }

    double a = atof(argv[1]);
    double b = atof(argv[2]);
    double c = atof(argv[3]);
    double d = atof(argv[4]);

    double ab, cd;
    struct args args_ab = {a, b, &ab};
    struct args args_cd = {c, d, &cd};

    pthread_t thread_ab, thread_cd;
    pthread_create(&thread_ab, NULL, multiply, &args_ab);
    pthread_create(&thread_cd, NULL, multiply, &args_cd);

    pthread_join(thread_ab, NULL);
    pthread_join(thread_cd, NULL);

    double result = ab + cd;
    printf("Result: %.2f\n", result);

    return 0;
}
