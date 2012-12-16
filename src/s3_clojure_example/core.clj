(ns s3-clojure-example.core
  (:require [clj-yaml.core :as yaml]))

(def s3-config-file
  "Location of the config file"
  "config/s3.yml")

(defn raw-s3-credentials
  "A raw string containing the S3 credentials read from the yaml file"
  ([] (slurp s3-config-file))
  ([filepath] (slurp filepath)))


(defn s3-credentials
  "A map of credentials read from the S3 yaml file"
  ([] (yaml/parse-string (raw-s3-credentials)))
  ([path] (yaml/parse-string (raw-s3-credentials path))))
