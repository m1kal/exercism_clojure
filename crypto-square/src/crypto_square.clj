(ns crypto-square)

(defn transpose [input]
  (loop [out [] cur input]
    (if (empty? (first cur))
      (clojure.string/join "" out)
      (recur
        (conj out (clojure.string/join "" (map first cur)))
        (map rest cur)))))

(defn normalize-plaintext [input]
  (-> input
    (clojure.string/replace #"[^a-zA-Z0-9]" "")
    clojure.string/lower-case))

(defn square-size [input]
  (-> input
    normalize-plaintext
    count
    Math/sqrt
    Math/ceil
    int))

(defn segments [input length]
  (map #(clojure.string/join "" %) (partition-all length input))
)

(defn plaintext-segments [input]
  (segments (normalize-plaintext input) (square-size input))
)

(defn ciphertext [input]
  (transpose (plaintext-segments input))
)

(defn sizes [input]
  (let [length (count (normalize-plaintext input))
        rows (square-size input)
        columns (int (Math/ceil (/ length rows)))
        short_rows (- (* rows columns) length)
        full_rows (- rows short_rows)]
        {:columns [columns (dec columns)]
         :split_point (* columns full_rows)}
  ))

(defn normalize-ciphertext [input]
  (let [output_sizes (sizes input)
        parts
        (->> input
          ciphertext
          (split-at (:split_point output_sizes)))]
  (clojure.string/join " "
    (apply concat
      (map segments parts (:columns output_sizes))))))
