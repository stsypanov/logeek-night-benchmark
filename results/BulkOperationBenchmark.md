**ArrayList**

|             |      |      |    Java 8 |       |    Java 9  |       |      |
|-------------|------|------|-----------|-------|------------|-------|------|
| Benchmark   | Mode |  Cnt |      Score| Error |       Score| Error | Unit |
| oneByOne    | avgt |   10 |  **98,9**|   0,9  |   **74,0**|  0,5  | ns/op|
| addAll      | avgt |   10 |  **55,2**|   1,1  |   **31,8**|  0,1  | ns/op|
| constructor | avgt |   10 |  **24,3**|   0,7  |   **18,9**|  0,0  | ns/op|
|-------------|------|------|-----------|-------|------------|-------|------|
| oneByOne    | avgt |  100 | **992,8**|  35,4  |  **745,5**|  3,9  | ns/op|
| addAll      | avgt |  100 | **225,9**|   4,7  |  **201,8**|  0,4  | ns/op|
| constructor | avgt |  100 | **115,1**|   1,3  |  **100,6**|  0,4  | ns/op|
|-------------|------|------|-----------|--------|-----------|-------|------|
| oneByOne    | avgt | 1000 |**9544,7**|  749,0 |**11372,8**| 53,8  | ns/op|
| addAll      | avgt | 1000 |**1915,8**|   63,9 | **1636,5**|  4,4  | ns/op|
| constructor | avgt | 1000 |**1080,9**|   36,4 |  **968,1**|  2,3  | ns/op|


**HashSet**

|             |      |      |    Java 8 |         |   Java 9   |       |      |
|-------------|------|------|-----------|---------|------------|-------|------|
| Benchmark   | Mode |  Cnt |      Score|  Error  |       Score| Error | Unit |
| oneByOne    | avgt |   10 |  **231,5**|  23,4   |  **184,8**|   0,9 | ns/op|
| addAll      | avgt |   10 |  **211,5**|   6,4   |  **189,1**|   1,1 | ns/op|
| constructor | avgt |   10 |  **201,0**|   4,3   |  **192,6**|   1,4 | ns/op|
|-------------|------|------|-----------|---------|------------|-------|------|
| oneByOne    | avgt |  100 | **2770,4**|  245,4  | **2540,2**|  14,8 | ns/op|
| addAll      | avgt |  100 | **2619,9**|  229,9  | **2593,0**|  14,8 | ns/op|
| constructor | avgt |  100 | **1711,7**|   12,3  | **1768,2**|   6,6 | ns/op|
|-------------|------|------|-----------|----------|-----------|-------|------|
| oneByOne    | avgt | 1000 |**22616,7**|  240,6  |**27070,8**| 175,6 | ns/op|
| addAll      | avgt | 1000 |**21913,1**|  623,7  |**27549,4**| 161,8 | ns/op|
| constructor | avgt | 1000 |**16270,0**|  128,1  |**18460,7**|  85,0 | ns/op|
