(ns dzik.giphy
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json])
  (:require [ring.util.codec :as codec]))

(defn get-original-url [response]
  (-> response
      :body
      json/read-str
      (get "data")
      (get "image_original_url")))

(defn random []
  (println "random image")
  (let [response (client/get (format "http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC") {:accept :json})]
    (-> response
        :body
        json/read-str
        (get "data")
        (get "image_original_url"))))

(defn search [term]

  (println (format "searching for image %s" term))
  (let [response (client/get
                  (format "http://api.giphy.com/v1/gifs/search?q=%s&api_key=dc6zaTOxFJmzC"
                          (codec/url-encode term)))]
    (-> response
        :body
        json/read-str
        (get "data")
        first
        (get "images")
        (get "original")
        (get "url"))))
