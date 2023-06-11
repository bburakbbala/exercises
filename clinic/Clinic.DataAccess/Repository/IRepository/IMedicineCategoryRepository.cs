using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IMedicineCategoryRepository : IRepositoryAsync<MedicineCategory>
    {
        void Update(MedicineCategory medicineCategory);
    }
}