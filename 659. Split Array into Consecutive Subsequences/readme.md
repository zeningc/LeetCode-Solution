# 659. Split Array into Consecutive Subsequences

### Map
Record the tail of each list and its len, everytime we found an element, try to extend the existing shortest list, if such list does not exist, creat a new one.