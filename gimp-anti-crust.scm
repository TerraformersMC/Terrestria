; gimp-anti-crust.scm, a GIMP Scheme Script (Script-Fu)
; Removes partially transparent pixels from images
;
; Place this file in your ~/.gimp-2.8/scripts directory, then use the following command in a directory to "un-crust" all the images:
; gimp -i -b "((batch-anti-crust \"*\") (gimp-quit 0))"
; The directory may not contain opaque images. It must be all transparent images.

(define (anti-crust filename)
	(let* 
		(
			(image (car (gimp-file-load RUN-NONINTERACTIVE filename filename)))
			(drawable (car (gimp-image-get-active-layer image)))
		)
		(plug-in-threshold-alpha RUN-NONINTERACTIVE image drawable 254)
		(gimp-file-save RUN-NONINTERACTIVE image drawable filename filename)
		(gimp-image-delete image)
	)
)


(define (batch-anti-crust pattern)
	(let* ((filelist (cadr (file-glob pattern 1))))
		(while (not (null? filelist))
			(let* ((filename (car filelist)))
			(print filename)
			(anti-crust filename)
			(set! filelist (cdr filelist)))
		)
	)
) 
