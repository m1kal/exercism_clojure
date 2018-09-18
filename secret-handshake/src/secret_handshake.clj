(ns secret-handshake)

(defn commands [number]
  (let [check-bit (fn [bit] (pos? (bit-and number (bit-shift-left 1 bit))))]
    (->> (range 4)
         (filter check-bit)
         (mapv ["wink" "double blink" "close your eyes" "jump"])
         ((if (check-bit 4) reverse identity)))))

