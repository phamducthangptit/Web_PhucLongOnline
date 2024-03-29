USE [PhucLongOnline]
GO
/****** Object:  StoredProcedure [dbo].[sp_tim_top_3]    Script Date: 19-01-2024 1:52:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[sp_tim_top_3]
AS
Begin
SELECT SanPham.*
FROM SanPham
WHERE SanPham.IdSanPham IN (
    SELECT TOP 3 CT_SanPham_Size.IdSanPham
    FROM CT_DonHang
    JOIN CT_SanPham_Size ON CT_DonHang.IdSanPhamSize = CT_SanPham_Size.IdSanPhamSize
    GROUP BY CT_SanPham_Size.IdSanPham
    ORDER BY SUM(CT_DonHang.SoLuong) DESC
);
end
GO
