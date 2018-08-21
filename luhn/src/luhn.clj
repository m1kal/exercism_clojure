(ns luhn)

(defn- strip-input [input]
  (re-matches #"^\d\d+$" (clojure.string/replace input #" " "")))

(defn- split-to-even-and-odd-subsequences [input]
  (map
    #(filter identity %)
    (map
      #(map
        %
        (partition-all
          2
          (reverse input)))
    [first second])))

(defn double-and-subtract [digit]
  (let [double (* 2 digit)]
    (if (> double 9) (- double 9) double)))

(defn- process-odd-digits [input]
  (map double-and-subtract input))

(defn process-digits [[even odd]]
  (reduce + (flatten [even (process-odd-digits odd)])))

(defn valid? [input]
  (if-let [stripped-input (strip-input input)]
    (zero?
      (mod
        (process-digits
          (split-to-even-and-odd-subsequences
            (map
              read-string
              (clojure.string/split stripped-input #""))))
        10))
    false))
