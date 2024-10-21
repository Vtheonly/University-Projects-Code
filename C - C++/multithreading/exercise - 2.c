//
// Created by mersel on 10/22/24.
//


#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct TypeTableau {
    int size;
    // I want this to be an array but i dont know the size so we just gonna do a pointer to be filled later on
    int *items;
    int isThereX;
};

int getRandomNumber(int max) {
    return rand() % max;}



void *initTypeTableau(void *arg) {
    int *size = (int *) arg;

    // the size is for the initial empty size of the struct and the entire arrays which is supposed to be 100 but whatever
    struct TypeTableau *current = malloc(sizeof(struct TypeTableau));
    current->size = *size;


    // we don't really know how much we will be using and we cant put a fixed number in the struct .....that would soooo much "1er anée Licence"......
    // also we might never reach the end of the predefined memory so it's gonna be leaked memory
    current->items = malloc(sizeof(int) * (*size));


    // i gotta seed the rand() function one time to get a way to "generate the randomness"
    srand(time(NULL));

    for (int i = 0; i < (*size); i++) {
        current->items[i] = getRandomNumber(100);}


    printf("\t the TypeTableau is filled  ...\n");

    return current;

}

void *searchTypeTableau(void *arg) {

    printf("\t searchTypeTableau is initiated  ...\n");

    struct TypeTableau *tableau = (struct TypeTableau *) arg;


    printf("The target is %d\n", tableau->isThereX);


    for (int i = 0; i < tableau->size; i++) {
        printf("%d - ", tableau->items[i]);

        if (tableau->items[i] == tableau->isThereX) {
            return (void *) 1;  // found
        }
    }

    return (void *) 0;  // nope
}


int main() {

    pthread_t creationThread, searchThread;
    struct TypeTableau *item;
    int size = 100;
    pthread_create(&creationThread, NULL, initTypeTableau, &size);
    pthread_join(creationThread, (void **) &item);




    int target;
    printf("which number are you looking for [0-99] : ");
    scanf("%d", &target);
    item->isThereX = target;


    pthread_create(&searchThread, NULL, searchTypeTableau, (void*) item);


    int result;
    pthread_join(searchThread, (void **) &result);

    printf("\n the result : %d\n", result);
    return 0;
}
