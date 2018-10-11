(ns rotational-cipher)

(defn- letter-index [letter base]
  (- (int letter) (int base)))

(defn- to-letter [idx base]
  (char (+ (int base) idx)))

(defn- shift-char [letter shift]
  (mod (+ letter shift) 26))

(defn- transform-with-base [letter shift base]
  (-> letter
      (letter-index base)
      (shift-char shift)
      (to-letter base)))

(defn- params [letter shift]
  (condp re-matches (str letter)
    #"[a-z]" [shift \a]
    #"[A-Z]" [shift \A]
    [0 letter]))

(defn- transform [letter shift]
  (apply transform-with-base letter (params letter shift)))

(defn rotate [input shift]
  (clojure.string/join (map #(transform % shift) input)))

