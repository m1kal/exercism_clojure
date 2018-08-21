(ns sieve)


(defn sieve [limit]
  (loop [primes (range 2 (inc limit)) number 2]
    (if (>= (* number number) limit)
      primes
      (let
        [new-primes
          (filter
            #(or (= % number) (not (zero? (mod % number))))
            primes)
        next-number
          (first
            (filter
              #(> % number)
              new-primes))]
        (recur new-primes next-number)))))
