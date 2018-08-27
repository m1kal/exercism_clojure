(ns luhn)

(defn- strip-input [input]
  (re-matches #"^\d\d+$" (clojure.string/replace input #" " "")))

(defn- split-to-even-and-odd-subsequences [input]
  (map
    #(->>
      input
      reverse
      (partition-all 2)
      (map %)
      (filter identity))
    [first second]))

(defn double-and-subtract [digit]
  (let [double (* 2 digit)]
    (if (> double 9) (- double 9) double)))

(defn- process-odd-digits [input]
  (map double-and-subtract input))

(defn process-digits [[even odd]]
  (reduce + (flatten [even (process-odd-digits odd)])))

(defn valid? [input]
  (if-let [stripped-input (strip-input input)]
    (->
      stripped-input
      (clojure.string/split #"")
      (#(map read-string %))
      split-to-even-and-odd-subsequences
      process-digits
      (mod 10)
      zero?)
    false))
