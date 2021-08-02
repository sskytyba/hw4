<b> Get request has single request to db table with user data with nearly 5_000_000 rows. </b>

<Table>
<thead>
  <th>Name</th>
  <th>
    Concurrency 10
  </th>
  <th>
    Concurrency 25
  </th>
  <th>
    Concurrency 50
  </th>
  </thead>    
  <tbody>
    <tr>
      <td>Direct call to db</td>
      <td>
        Transactions:                   1157 hits  <br>
Availability:                100.00 %  <br>
Elapsed time:                 199.78 secs  <br>
Data transferred:               0.26 MB  <br>
Response time:                  1.72 secs  <br>
Transaction rate:               5.79 trans/sec  <br>
Throughput:                     0.00 MB/sec  <br>
Concurrency:                    9.94  <br>
Successful transactions:        1157  <br>
Failed transactions:               0  <br>
Longest transaction:            4.11  <br>
Shortest transaction:           0.69 
      </td>
  <td>Transactions:                   1064 hits  
Availability:                100.00 %  
Elapsed time:                 200.02 secs  
Data transferred:               0.25 MB  
Response time:                  4.63 secs  
Transaction rate:               5.32 trans/sec  
Throughput:                     0.00 MB/sec  
Concurrency:                   24.62  
Successful transactions:        1064  
Failed transactions:               0  
Longest transaction:            7.79  
Shortest transaction:           0.92</td>
  <td>Transactions:                   1076 hits  
Availability:                 100.00 %  
Elapsed time:                 199.96 secs  
Data transferred:               0.25 MB  
Response time:                  9.08 secs  
Transaction rate:               5.38 trans/sec  
Throughput:                     0.00 MB/sec  
Concurrency:                   48.87  
Successful transactions:        1076  
Failed transactions:               0  
Longest transaction:           17.56  
Shortest transaction:           0.65  </td>
    </tr>
    <tr>
  <td>Cached call</td>
    <td>Transactions:                  71312 hits  
Availability:                 100.00 %  
\Elapsed time:                199.08 secs  
Data transferred:              16.36 MB  
Response time:                  0.02 secs  
Transaction rate:             358.20 trans/sec  
Throughput:                     0.08 MB/sec  
Concurrency:                    8.37  
Successful transactions:       71313  
Failed transactions:               0  
Longest transaction:            1.05  
Shortest transaction:           0.00  </td>
      <td>Transactions:                  77455 hits  
\Availability:                100.00 %  
Elapsed time:                 199.33 secs  
Data transferred:              17.77 MB  
Response time:                  0.05 secs  
Transaction rate:             388.58 trans/sec  
Throughput:                     0.09 MB/sec  
Concurrency:                   18.14  
Successful transactions:       77458  
Failed transactions:               0  
Longest transaction:            2.74  
Shortest transaction:           0.00  </td>
        <td>Transactions:                  83549 hits  
\Availability:                100.00 %  
Elapsed time:                 199.99 secs  
Data transferred:              19.16 MB  
Response time:                  0.07 secs  
Transaction rate:             417.76 trans/sec  
Throughput:                     0.10 MB/sec  
Concurrency:                   31.08  
Successful transactions:       83549  
Failed transactions:               0  
Longest transaction:            4.19  
Shortest transaction:           0.00  </td>
    </tr>
    <tr>
  <td>Cached call with probabilistic early expiration</td>
    <td>Transactions:                  73056 hits  
Availability:                 100.00 %  
Elapsed time:                 199.84 secs  
Data transferred:              16.76 MB  
Response time:                  0.02 secs  
Transaction rate:             365.57 trans/sec  
Throughput:                     0.08 MB/sec  
Concurrency:                    8.51  
Successful transactions:       73056  
Failed transactions:               0  
Longest transaction:            1.05  
Shortest transaction:           0.00  </td>
      <td>Transactions:                 117954 hits  
\Availability:                100.00 %  
Elapsed time:                 199.25 secs  
Data transferred:              27.06 MB  
Response time:                  0.03 secs  
Transaction rate:             591.98 trans/sec  
Throughput:                     0.14 MB/sec  
Concurrency:                   17.85  
Successful transactions:      117954  
Failed transactions:               0  
Longest transaction:            2.38  
Shortest transaction:           0.00  </td>
        <td>Transactions:                 101020 hits  
\Availability:                100.00 %  
Elapsed time:                 199.40 secs  
Data transferred:              23.16 MB  
Response time:                  0.06 secs  
Transaction rate:             506.62 trans/sec  
Throughput:                     0.12 MB/sec  
Concurrency:                   31.46  
Successful transactions:      101020  
Failed transactions:               0  
Longest transaction:            3.75  
Shortest transaction:           0.00  </td>
    </tr>
    
  </tbody> 
  </table>


<b>Get from db:</b>

./siege -c10  -d1s -t60s -b --verbose -f .\getUrls
Transactions:                   1157 hits  <br>
\Availability:                100.00 %  <br>
Elapsed time:                 199.78 secs  <br>
Data transferred:               0.26 MB  <br>
Response time:                  1.72 secs  <br>
Transaction rate:               5.79 trans/sec  <br>
Throughput:                     0.00 MB/sec  <br>
Concurrency:                    9.94  <br>
Successful transactions:        1157  <br>
Failed transactions:               0  <br>
Longest transaction:            4.11  <br>
Shortest transaction:           0.69 

./siege -c25  -d1s -t60s -b --verbose -f .\getUrls  
Transactions:                   1064 hits  
\Availability:                100.00 %  
Elapsed time:                 200.02 secs  
Data transferred:               0.25 MB  
Response time:                  4.63 secs  
Transaction rate:               5.32 trans/sec  
Throughput:                     0.00 MB/sec  
Concurrency:                   24.62  
Successful transactions:        1064  
Failed transactions:               0  
Longest transaction:            7.79  
Shortest transaction:           0.92  

./siege -c50  -d1s -t200s -b --verbose -f .\getUrls  
Transactions:                   1076 hits  
Availability:                 100.00 %  
Elapsed time:                 199.96 secs  
Data transferred:               0.25 MB  
Response time:                  9.08 secs  
Transaction rate:               5.38 trans/sec  
Throughput:                     0.00 MB/sec  
Concurrency:                   48.87  
Successful transactions:        1076  
Failed transactions:               0  
Longest transaction:           17.56  
Shortest transaction:           0.65  


<b> Get from cache with lock for single key update: </b>

./siege -c10  -d1s -t200s -b --verbose -f .\getCachedUrls  
Transactions:                  71312 hits  
Availability:                 100.00 %  
\Elapsed time:                199.08 secs  
Data transferred:              16.36 MB  
Response time:                  0.02 secs  
Transaction rate:             358.20 trans/sec  
Throughput:                     0.08 MB/sec  
Concurrency:                    8.37  
Successful transactions:       71313  
Failed transactions:               0  
Longest transaction:            1.05  
Shortest transaction:           0.00  

./siege -c25  -d1s -t200s -b --verbose -f .\getCachedUrls  
Transactions:                  77455 hits  
\Availability:                100.00 %  
Elapsed time:                 199.33 secs  
Data transferred:              17.77 MB  
Response time:                  0.05 secs  
Transaction rate:             388.58 trans/sec  
Throughput:                     0.09 MB/sec  
Concurrency:                   18.14  
Successful transactions:       77458  
Failed transactions:               0  
Longest transaction:            2.74  
Shortest transaction:           0.00  

./siege -c50  -d1s -t200s -b --verbose -f .\getCachedUrls  
Transactions:                  83549 hits  
\Availability:                100.00 %  
Elapsed time:                 199.99 secs  
Data transferred:              19.16 MB  
Response time:                  0.07 secs  
Transaction rate:             417.76 trans/sec  
Throughput:                     0.10 MB/sec  
Concurrency:                   31.08  
Successful transactions:       83549  
Failed transactions:               0  
Longest transaction:            4.19  
Shortest transaction:           0.00  

<b>Get from cache with lock for single key update and probabilistic early expiration: </b>

./siege -c10  -d1s -t200s -b --verbose -f .\getCachedProbabilityUrls  
Transactions:                  73056 hits  
Availability:                 100.00 %  
Elapsed time:                 199.84 secs  
Data transferred:              16.76 MB  
Response time:                  0.02 secs  
Transaction rate:             365.57 trans/sec  
Throughput:                     0.08 MB/sec  
Concurrency:                    8.51  
Successful transactions:       73056  
Failed transactions:               0  
Longest transaction:            1.05  
Shortest transaction:           0.00  

./siege -c25  -d1s -t200s -b --verbose -f .\getCachedProbabilityUrls  
Transactions:                 117954 hits  
\Availability:                100.00 %  
Elapsed time:                 199.25 secs  
Data transferred:              27.06 MB  
Response time:                  0.03 secs  
Transaction rate:             591.98 trans/sec  
Throughput:                     0.14 MB/sec  
Concurrency:                   17.85  
Successful transactions:      117954  
Failed transactions:               0  
Longest transaction:            2.38  
Shortest transaction:           0.00  

./siege -c50  -d1s -t200s -b --verbose -f .\getCachedProbabilityUrls  
Transactions:                 101020 hits  
\Availability:                100.00 %  
Elapsed time:                 199.40 secs  
Data transferred:              23.16 MB  
Response time:                  0.06 secs  
Transaction rate:             506.62 trans/sec  
Throughput:                     0.12 MB/sec  
Concurrency:                   31.46  
Successful transactions:      101020  
Failed transactions:               0  
Longest transaction:            3.75  
Shortest transaction:           0.00  
