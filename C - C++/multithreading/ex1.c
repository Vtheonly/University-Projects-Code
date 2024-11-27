#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

typedef struct {
    int *array;
    int size;
    int x;
} TypeTableau;

void *fill_array(void *arg) {
    TypeTableau *tt = (TypeTableau *)arg;
    srand(time(NULL));
    for (int i = 0; i < tt->size; i++) {
        tt->array[i] = rand() % 10;
    }
    pthread_exit(NULL);
}

void *search_x(void *arg) {
    TypeTableau *tt = (TypeTableau *)arg;
    int found = 0;
    for (int i = 0; i < tt->size; i++) {
        if (tt->array[i] == tt->x) {
            found = 1;
            break;
        }
    }
    int *result = malloc(sizeof(int));
    *result = found;
    pthread_exit(result);
}

int main() {
    int size, x;
    printf("Enter the size of the array: ");
    scanf("%d", &size);
    printf("Enter the value of x: ");
    scanf("%d", &x);

    TypeTableau tt;
    tt.array = malloc(size * sizeof(int));
    tt.size = size;
    tt.x = x;

    pthread_t fill_thread, search_thread;
    pthread_create(&fill_thread, NULL, fill_array, &tt);
    pthread_join(fill_thread, NULL);

    pthread_create(&search_thread, NULL, search_x, &tt);
    int *result;
    pthread_join(search_thread, (void **)&result);

    printf("The value of x is %s in the array.\n", *result ? "present" : "not present");

    free(tt.array);
    free(result);
    return 0;
}
