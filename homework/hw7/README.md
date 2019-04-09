## Part A: Test-first development

* __AvlTreeMapTest:__ One of the problems I encountered was the fact that my rotation functions were all private. Because of this, it was hard to test the functions on their own. In order to resolve this, I created public tester functions that showed the level order before and after a rotation. Test cases that I coded were as follows (starred numbers are added last):
  * Single right:
  
                   30                       25
                  /  \                    /    \
                 25   31                20      30
                /  \        --->          \    /  \
               20   26                   *21* 26  31
                 \
                 *21*
             
  * Single left:
  
                   20                       25
                  /  \                    /    \
                16    25     --->        20      30
                     /  \               /  \    /
                   21    30            16  21 *26* 
                        /  
                      *26*  
                      
  * Double right left:
  
                   20                       25
                  /  \                    /    \
                 16  30                 20      30
                    /  \    --->       /  \       \
                   25   31            16  *21*     31
                  /    
                *21*         
                      
  * Double left right:
  
                   30                       25
                  /  \                    /    \
                 20   31                20      30
                /  \        --->       /       /  \
               16   25                16     *26*  31
                      \
                      *26*    
                  
* __TreapMapTest:__ 