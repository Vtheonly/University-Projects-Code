# MIPS Assembly Program
# Author: 
# Date: 

.text
.globl main

main:
    # Your MIPS assembly code starts here
    
    # Exit the program
    li $v0, 10      # syscall code for exit
    syscall         # invoke system call
