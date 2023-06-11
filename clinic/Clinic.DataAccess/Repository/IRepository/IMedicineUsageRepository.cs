using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IMedicineUsageRepository : IRepositoryAsync<MedicineUsage>
    {
        void Update(MedicineUsage medicineUsage);
    }
}