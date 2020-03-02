# SPN  CS6331 HW1  
This program implement an SPN 8 round of 4 S-box each, each S-box take as input 8 bits. So the program can deal with 32 bits message each time.  
  
## Java version  
This code needs to run in a java 8.  
## How to run this code  
**java -jar spn.jar**  
You should keep the input_spn.txt and spn.jar at the same folder.
## How to set the input  
Every input message need type into input_spn.txt.  
Here the numbers '0' and '1' are used to simulate the 0 and 1 in binary.
### Key  
A key K of length 36 bytes(288 bits).   
Then, you can enter 288 unordered '0' and '1' as key in one line.  

```101010010100110101010011011000101010010001001111011110000010101010101001010011010101001101100010101001000100111101111000001010101010100101001101010100110110001010100100010011110111100000101010101010010100110101010011011000101010010001001111011110000010101010101001010011010101001101100010```

### S-box  
The S-box function *f* taking a byte as input and providing a byte as output.  
Each S-box needs to convert an 8-bit binary number to a new binary number. Because 8 bits binary number can repersent 256 numbers.
So I replace 8-bit binary numbers with 256 decimal numbers, such as 0001 1001 --> 33.  
So enter 0 to 255 in different order to repersent different S-box.

```255,254,253,252,251,250,249,248,247,246,245,244,243,242,241,240,239,238,237,236,235,234,233,232,231,230,229,228,227,226,225,224,223,222,221,220,219,218,217,216,215,214,213,212,211,210,209,208,207,206,205,204,203,202,201,200,199,198,197,196,195,194,193,192,191,190,189,188,187,186,185,184,183,182,181,180,179,178,177,176,175,174,173,172,171,170,169,168,167,166,165,164,163,162,161,160,159,158,157,156,155,154,153,152,151,150,149,148,147,146,145,144,143,142,141,140,139,138,137,136,135,134,133,132,131,130,129,128,127,126,125,124,123,122,121,120,119,118,117,116,115,114,113,112,111,110,109,108,107,106,105,104,103,102,101,100,99,98,97,96,95,94,93,92,91,90,89,88,87,86,85,84,83,82,81,80,79,78,77,76,75,74,73,72,71,70,69,68,67,66,65,64,63,62,61,60,59,58,57,56,55,54,53,52,51,50,49,48,47,46,45,44,43,42,41,40,39,38,37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0```


### Permutation
The permutation function $\sigma$ permutation 32 bits.  
So I use 0 to 31 to represent how to permutation 32 bits.

```0,26,8,13,2,16,14,22,1,5,29,11,30,12,17,27,28,20,18,10,25,3,4,19,6,9,21,7,15,23,24,31```

### *m*  
An integer that is a multiple of 32.

### *a*
An integer *a* value 0 indicating encryption, and value 1 indicating decryption.

## What is output  
The output_spn.txt file will first show the operation. If the operation of the file is encrypted, return the encryption. 
If the operation of the file is decrypted, return the decryption.   
Other output are same as input file.
   
## Test  
Because this progarm provide both encryption and decryption function. You can use this program encryption first, then use the 
output result as a new message and revise a = 1. The result of the decryption will equal to the raw message.
